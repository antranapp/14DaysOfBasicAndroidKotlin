package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import org.w3c.dom.Text

class ProgresbarActivity : AppCompatActivity() {

    private lateinit var circularProgressbar: ProgressBar
    private lateinit var circularProgressLabel: TextView
    private lateinit var circularProgressButton: Button

    private lateinit var horizontalProgressBar: ProgressBar
    private lateinit var horizontalProgressLabel: TextView
    private lateinit var horizontalProgressButton: Button

    private var circularHandler = Handler()
    private var horizontalHandler = Handler()

    private var circularProgress = 0
    private var horizontalProgress = 0

    private var isCircularStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progresbar)

        title = "Progressbar"

        circularProgressbar = findViewById(R.id.id_progressbar_circular)
        circularProgressbar.visibility = View.GONE
        circularProgressLabel = findViewById(R.id.id_progressbar_circular_label)
        circularProgressButton = findViewById(R.id.id_progressbar_circular_button)

        horizontalProgressBar = findViewById(R.id.id_progressbar_horizontal)
        horizontalProgressLabel = findViewById(R.id.id_progressbar_horizontal_label)
        horizontalProgressButton = findViewById(R.id.id_progressbar_horizontal_button)
    }

    fun onCircularButtonClicked(v: View) {
        Thread(Runnable {
            // dummy thread mimicking some operation whose progress cannot be tracked

            // display the indefinite progressbar
            this@ProgresbarActivity.runOnUiThread(java.lang.Runnable {
                circularProgressbar.visibility = View.VISIBLE
                circularProgressLabel.visibility = View.VISIBLE
                circularProgressButton.isEnabled = false
            })

            // performing some dummy time taking operation
            try {
                circularProgress = 0;
                while (circularProgress < 100) {

                    circularProgress += 5

                    // Update the progress bar and display the current value
                    circularHandler.post(Runnable {
                        circularProgressbar.progress = circularProgress
                        circularProgressLabel.text = circularProgress.toString() + "/" + circularProgressbar.max
                    })

                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // when the task is completed, make progressBar gone
            this@ProgresbarActivity.runOnUiThread(java.lang.Runnable {
                circularProgressbar.visibility = View.GONE
                circularProgressLabel.visibility = View.GONE
                circularProgressButton.isEnabled = true
            })
        }).start()
    }

    fun onHorizontalButtonClicked(v: View) {
        horizontalProgress = 0

        Thread(Runnable {
            while (horizontalProgress < 100) {
                horizontalProgress += 1
                // Update the progress bar and display the current value
                horizontalHandler.post(Runnable {
                    horizontalProgressBar.progress = horizontalProgress
                    horizontalProgressLabel.text = horizontalProgress.toString() + "/" + horizontalProgressBar.max
                })
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()
    }
}
