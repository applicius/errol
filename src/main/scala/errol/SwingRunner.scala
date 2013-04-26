package errol

import javax.swing._
import java.awt.event._
import java.awt.{ GridLayout, Insets, Component }
import java.beans.{ PropertyChangeListener, PropertyChangeEvent }

import org.specs2.execute.Details
import org.specs2.mutable.Specification
import org.specs2.main.Arguments
import org.specs2.runner.ClassRunner
import org.specs2.reporter.{ NotifierReporter, MessagesNotifier, Reporter }
import org.specs2.specification.SpecificationStructure

trait SwingRunner extends JFrame { self ⇒
  def subReporter: Reporter
  def specification: Specification
  def buttonLabel: String
  def runningMessage: String
  def endMessage: String
  def frameTitle: String

  def beforeClosing {}

  val panel = new JPanel()

  panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5))
  panel.setLayout(new GridLayout(3, 1, 5, 5))
  getContentPane.add(panel)

  val label = new JLabel(runningMessage)

  val progressbar = new JProgressBar(0, 100)
  progressbar.setValue(0)
  progressbar.setStringPainted(true)

  val button = new JButton(buttonLabel)
  button.setEnabled(false)
  button.setMargin(new Insets(2, 2, 2, 2))
  button.setSize(70, 30)
  button.addActionListener(new ActionListener() {
    def actionPerformed(event: ActionEvent) {
      beforeClosing
      sys.exit(0)
    }
  })

  panel.add(label)
  panel.add(progressbar)
  panel.add(button)

  setTitle(frameTitle)
  setSize(400, 150)
  setResizable(false)
  setLocationRelativeTo(null)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  def main(args: Array[String]) {
    SwingUtilities.invokeLater(new Runnable() {
      def run {
        val task = new Task()
        task.addPropertyChangeListener(new PropertyChangeListener() {
          def propertyChange(e: PropertyChangeEvent) {
            if ("progress" == e.getPropertyName) {
              val progress = e.getNewValue.asInstanceOf[Int]
              progressbar.setValue(progress)
            }
          }
        })

        self.setVisible(true)
        task.execute()
      }
    })
  }

  class Task extends SwingWorker[Unit, Unit] {
    def doInBackground {
      val task = this
      val structure = self.specification.is
      val steps: Float = structure.examples.size
      val incr = Math.round(100f / steps)

      val run = new ClassRunner {
        override lazy val reporter = new CustomReporter {
          protected val sub = self.subReporter
          val notifier = new SwingNotifier(task, incr)
        }
      }

      run(self.specification)
    }

    override def done {
      setProgress(100)
      button.setEnabled(true)
      label.setText(s"""<html><div style="width: 300px"><bold>$endMessage</bold></div></html>""")
    }

    def reportProgress(i: Int) = setProgress(i)
  }

  trait CustomReporter extends NotifierReporter {
    protected val sub: Reporter

    override def report(spec: SpecificationStructure)(implicit args: Arguments) = {
      super.report(spec)
      sub.report(spec)
    }
  }

  class SwingNotifier(task: Task, incr: Int) extends MessagesNotifier {

    // progression
    var i = 0

    private def reportProgress {
      i += incr
      task.reportProgress(Math.min(i, 99))
    }

    override def exampleSuccess(title: String, duration: Long) {
      reportProgress
    }

    override def exampleFailure(name: String, message: String, location: String, t: Throwable, details: Details, duration: Long) {
      reportProgress
    }

    override def exampleSkipped(name: String, message: String, duration: Long) {
      reportProgress
    }

    override def exampleError  (name: String, message: String, location: String, f: Throwable, duration: Long) {
      reportProgress
    }
  }
}
