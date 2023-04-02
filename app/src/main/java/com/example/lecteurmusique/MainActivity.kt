package com.example.lecteurmusique

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lecteurmusique.adapter.MusicPlayerAdapter
import com.example.lecteurmusique.model.MusicPlayerModel

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lvMain)
        val adapter = MusicPlayerAdapter(this, generateMusics())
        listView.adapter = adapter

        val songTitleTextView = findViewById<TextView>(R.id.song_title)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (currentPosition != position) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this, adapter.getItem(position).sound)
                mediaPlayer?.start()
                currentPosition = position
            } else {
                togglePlayPause()
            }
            updateSongTitle(songTitleTextView, adapter.getItem(position).title)
        }

        findViewById<Button>(R.id.play_pause_button).setOnClickListener {
            togglePlayPause()
        }

        findViewById<Button>(R.id.next_button).setOnClickListener {
            playNext(adapter, songTitleTextView)
        }

        findViewById<Button>(R.id.prev_button).setOnClickListener {
            playPrevious(adapter, songTitleTextView)
        }
    }

    //Play & Pause

    private fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            } else {
                it.start()
            }
        }
    }

    //Next Music

    private fun playNext(adapter: MusicPlayerAdapter, songTitleTextView: TextView) {
        if (currentPosition < adapter.count - 1) {
            currentPosition++
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, adapter.getItem(currentPosition).sound)
            mediaPlayer?.start()
            updateSongTitle(songTitleTextView, adapter.getItem(currentPosition).title)
        }
    }

    //Previous Music

    private fun playPrevious(adapter: MusicPlayerAdapter, songTitleTextView: TextView) {
        if (currentPosition > 0) {
            currentPosition--
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, adapter.getItem(currentPosition).sound)
            mediaPlayer?.start()
            updateSongTitle(songTitleTextView, adapter.getItem(currentPosition).title)
        }
    }

    private fun updateSongTitle(songTitleTextView: TextView, title: String) {
        songTitleTextView.text = title
    }

    //Setting the Music List

    fun generateMusics(): ArrayList<MusicPlayerModel> {
        return arrayListOf(
            MusicPlayerModel(
                R.drawable.ot2,
                "Octopath Traveler 2",
                "3:17",
                "Yasunori Nishiki",
                0,
                R.raw.ot2_main_theme
            ),
            MusicPlayerModel(
                R.drawable.ochette,
                "Ochette, the Hunter",
                "4:17",
                "Yasunori Nishiki",
                1,
                R.raw.ochette_the_hunter
            ),
            MusicPlayerModel(
                R.drawable.castti,
                "Castti, the Apothecary",
                "3:44",
                "Yasunori Nishiki",
                2,
                R.raw.castti_the_apothecary
            ),
            MusicPlayerModel(
                R.drawable.throne,
                "Throné, the Thief",
                "3:09",
                "Yasunori Nishiki",
                3,
                R.raw.throne_the_thief
            ),
            MusicPlayerModel(
                R.drawable.osvald,
                "Osvald, the Scholar",
                "4:03",
                "Yasunori Nishiki",
                4,
                R.raw.osvald_the_scholar
            ),
            MusicPlayerModel(
                R.drawable.partitio,
                "Partitio, the Merchant",
                "3:29",
                "Yasunori Nishiki",
                5,
                R.raw.partitio_the_merchant
            ),
            MusicPlayerModel(
                R.drawable.agnea,
                "Agnea, the Dancer",
                "3:32",
                "Yasunori Nishiki",
                6,
                R.raw.agnea_the_dancer
            ),
            MusicPlayerModel(
                R.drawable.temenos,
                "Temenos, the Cleric",
                "3:01",
                "Yasunori Nishiki",
                7,
                R.raw.temenos_the_cleric
            ),
            MusicPlayerModel(
                R.drawable.hikari,
                "Hikari, the Warrior",
                "3:21",
                "Yasunori Nishiki",
                8,
                R.raw.hikari_the_warrior
            ),
            MusicPlayerModel(
                R.drawable.ot2,
                "Critical Clash 2",
                "6:12",
                "Yasunori Nishiki",
                9,
                R.raw.criticalclash2
            ),

            )
        }
    }
