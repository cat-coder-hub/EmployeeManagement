package ps.mohe.employeemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_display.*

class Display : AppCompatActivity() {

    lateinit var db : MyDB
    lateinit var e : Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        db = MyDB(this)
        display()
    }

    fun search(view: View){
        val inn = searchTxt.text.toString()
        e = Employee(null, inn, inn, null)
        val data = db.search(e)
        val adp = EmpAdapter(this, data)
        myLv.adapter = adp
    }

    fun display(){
        val data = db.show()
        val adp = EmpAdapter(this, data)
        myLv.adapter = adp
    }
}