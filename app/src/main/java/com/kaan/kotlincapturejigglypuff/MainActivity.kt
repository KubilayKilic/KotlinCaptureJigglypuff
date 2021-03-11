package com.kaan.kotlincapturejigglypuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ImageArray bütün görüntüleri Diziye(array) eklemek

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)

        hideImages()


        //CountdownTimer
        object : CountDownTimer(20700, 1000) {
            override fun onFinish() {
                timeText.text = "Kalan Süre: 0"

                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                //alert
                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Oyun Bitti")
                alert.setMessage("Tekrar Oynamak İster Misin?")
                alert.setPositiveButton("Evet") {dialog, which ->
                    //restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                    //oyun tekrar başlamadan kısa bir süre bekler.
                    Thread.sleep(500)

                }
                alert.setNegativeButton("Hayır") {dialog, which ->
                    Toast.makeText(this@MainActivity, "●︿●", Toast.LENGTH_LONG).show()

                }
                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Kalan Süre: " + millisUntilFinished/1000
            }

        }.start()
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                //görselleri gizlemek
                //image arraydeki her görseli alıp image'a tanımla ve her birini invisible yap
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                    //rastgele olarak görselleri görünür yapmak
                    val random = Random()
                    val randomIndex = random.nextInt(16)
                    imageArray[randomIndex].visibility = View.VISIBLE

                    handler.postDelayed(runnable, 500)
                }
            }

        handler.post(runnable)

    }

    fun increaseScore(view : View) {
        //görüntülerin onClick metodu aktifleştiğinde(tıklandığında) skor 1 artar.
        score++
        //scoreText kısmında bu işlem gösterilir.
        scoreText.text = "Skor: $score"
    }
}