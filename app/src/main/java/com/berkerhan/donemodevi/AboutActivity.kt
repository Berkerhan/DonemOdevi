package com.berkerhan.donemodevi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.io.File

class AboutActivity : AppCompatActivity() {

    lateinit var developerImage: ImageView
    lateinit var developerName: TextView
    lateinit var developerNumber: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle("About")

        developerImage=findViewById(R.id.developer_image)
        developerName=findViewById(R.id.textview_name_surname)
        developerNumber=findViewById(R.id.textview_number)

        developerImage.setBackgroundResource(R.mipmap.zfoto)
        developerName.setText("Adı-Soyadı: Berker ERHAN")
        developerNumber.setText("Öğrenci no: 191030023")
    }
}