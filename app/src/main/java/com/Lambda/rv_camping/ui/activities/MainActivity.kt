package com.Lambda.rv_camping.ui.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.Lambda.rv_camping.R
import com.Lambda.rv_camping.model.CampingSpots
import com.Lambda.rv_camping.ui.controllers.LoginController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var player: MediaPlayer? = null
    var test: Int = R.layout.activity_main
        get()= field
        set(value){
            field = value
        }
    private lateinit var router: Router
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.cl_activity_main_parent)
    }
    companion object {
        val campingList = mutableListOf(
            CampingSpots("djlkj", "1","1 sams sd", 1f),
            CampingSpots("ds", "2","fsd", 42f)

        )
        val BUNDLE_KEY = "key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(test)




        player = MediaPlayer.create(this, R.raw.accomplished)

        /*vRecycle.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerRVAdapter(campingList)

        }*/

        vRecycle?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            //adapter = PropertiesAdapter(MainController.propertyList)
        }

        // Could have also just use cl_activity_main_parent instead of container
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(LoginController()))



        }





    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.als_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_see_on_map -> {

                player?.start()
                startActivity(Intent(this, ShowMapActivity::class.java))
            }
            R.id.menu_sort -> {
                player?.start()
                Toast.makeText(this, "Your content has been added to the bottom", Toast.LENGTH_LONG).show()
            }
            R.id.menu_sign_out -> {
                player?.start()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onBackPressed(){
        player?.start()
        router.popCurrentController()
    }


}
