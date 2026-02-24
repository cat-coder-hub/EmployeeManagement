package ps.mohe.employeemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val name = intent.getStringExtra("name")
        val role = intent.getStringExtra("role")
        val time = intent.getDoubleExtra("dailyTime", 0.0)

        val sph = mapOf(
            "عامل" to 5.0,
            "موظف" to 7.0,
            "مدير" to 10.0
        )

        val salaryph = sph[role] ?: 0.0
        val total_h = time * 30
        val salary = total_h * salaryph

        infoName.text = name
        infoRole.text = role
        infoHourSalary.text = "$salaryph $"
        infoDailyTime.text = "$time ساعة"
        infoTotalHours.text = "$total_h ساعة"
        infoTotalSalary.text = "$salary $"
    }
}