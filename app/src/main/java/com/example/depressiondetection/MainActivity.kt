package com.example.depressiondetection

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivity : ComponentActivity() {
    var predict: Button? = null
    var result: TextView? = null
    var rg_se_1: RadioGroup? = null
    var rg_se_2: RadioGroup? = null
    var rg_se_3: RadioGroup? = null
    var rg_se_4: RadioGroup? = null
    var rg_se_5: RadioGroup? = null
    var rg_se_6: RadioGroup? = null
    var rg_se_7: RadioGroup? = null
    var rg_se_8: RadioGroup? = null
    var rg_se_9: RadioGroup? = null
    var rg_se_10: RadioGroup? = null
//    var url = "https://student-placement-app.herokuapp.com/predict"
//    var url = "http://127.0.0.1:5000/predict"
    var url = "https://freeznix.pythonanywhere.com/predict"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questioner)
        addListenerOnButton()
    }

    private var btnSubmit: Button? = null
    private var hasil:TextView? = null

    fun addListenerOnButton() {
        btnSubmit = findViewById(R.id.btn_submit) as Button
        hasil = findViewById(R.id.tv_hasil) as TextView
        btnSubmit!!.setOnClickListener {
            // get selected radio button from radioGroup

            // find the radiobutton by returned id
//            radioButton = findViewById<View>(selectedId) as RadioButton
            var cal = calculate_self_esteem()
            var mental_health_history = get_mental_health_history()
            var headache = get_headache()
            var blood_pressure = get_blood_pressure()
            var sleep_quality = get_sleep_quality()
            var breathing_problem = get_breathing_problem()
            var noise_level = get_noise_level()
            var living_conditions = get_living_conditions()
            var safety = get_safety()
            var basic_needs = get_basic_needs()
            var academic_performance = get_academic_performance()
            var study_load = get_study_load()
            var teacher_student_relationship = get_teacher_student_relationship()
            var future_career_concerns = get_future_career_concerns()
            var social_support = get_social_support()
            var peer_pressure = get_peer_pressure()
            var extracurricular_activities = get_extracurricular_activities()
            var bullying =get_bullying()

            // Test
//            hasil!!.text = cal.toString()
//            hasil!!.text = mental_health_history.toString()
//            hasil!!.text = headache.toString()
//            hasil!!.text = blood_pressure.toString()
//            hasil!!.text = sleep_quality.toString()
//            hasil!!.text = breathing_problem.toString()
//            hasil!!.text = noise_level.toString()
//            hasil!!.text = living_conditions.toString()
//            hasil!!.text = safety.toString()
//            hasil!!.text = basic_needs.toString()
//            hasil!!.text = academic_performance.toString()
//            hasil!!.text = study_load.toString()
//            hasil!!.text = teacher_student_relationship.toString()
//            hasil!!.text = future_career_concerns.toString()
//            hasil!!.text = social_support.toString()
//            hasil!!.text = peer_pressure.toString()
//            hasil!!.text = extracurricular_activities.toString()
//            hasil!!.text = bullying.toString()

            val queue = Volley.newRequestQueue(this)
            // hit the API -> Volley
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST, url, { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val data = jsonObject.getString("placement")
                        if (data == "0") {
                            hasil!!.text = "Tidak Depresi atau Depresi Ringan"
                        } else if(data == "1"){
                            hasil!!.text = "Depresi Sedang"
                        } else if(data == "2"){
                            hasil!!.text = "Depresi Berat"
                        }else{
                            hasil!!.text = "Ada error"
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(
                        this@MainActivity,
                        error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val hashMap = HashMap<String, String>()
//                    hashMap.put("name", "peter")
//                    self_esteem:0.7
                    hashMap.put("self_esteem", cal.toString())
//                    mental_health_history:0.0
                    hashMap.put("mental_health_history", mental_health_history.toString())
//                    headache:0.5
                    hashMap.put("headache", headache.toString())
//                    blood_pressure:0.0
                    hashMap.put("blood_pressure", blood_pressure.toString())
//                    sleep_quality:0.5
                    hashMap.put("sleep_quality", sleep_quality.toString())
//                    breathing_problem:0.5
                    hashMap.put("breathing_problem", breathing_problem.toString())
//                    noise_level:0.5
                    hashMap.put("noise_level", noise_level.toString())
//                    living_conditions:0.5
                    hashMap.put("living_conditions", living_conditions.toString())
//                    safety:0.5
                    hashMap.put("safety", safety.toString())
//                    basic_needs:0.5
                    hashMap.put("basic_needs", basic_needs.toString())
//                    academic_performance:0.5
                    hashMap.put("academic_performance", academic_performance.toString())
//                    study_load:0.5
                    hashMap.put("study_load", study_load.toString())
//                    teacher_student_relationship:0.5
                    hashMap.put("teacher_student_relationship", teacher_student_relationship.toString())
//                    future_career_concern:0.5
                    hashMap.put("future_career_concern", future_career_concerns.toString())
//                    social_support:1.0
                    hashMap.put("social_support", social_support.toString())
//                    peer_pressure:0.5
                    hashMap.put("peer_pressure", peer_pressure.toString())
//                    extracurricular_activities:0.5
                    hashMap.put("extracurricular_activities", extracurricular_activities.toString())
//                    bullying:0.5
                    hashMap.put("bullying", bullying.toString())
                    return hashMap
                }
            }
            queue.add(stringRequest)
        }

    }

    fun calculate_self_esteem():Double {
        var calculate:Double = 0.0
        rg_se_1 = findViewById<View>(R.id.self_esteem_1) as RadioGroup
        rg_se_2 = findViewById<View>(R.id.self_esteem_2) as RadioGroup
        rg_se_3 = findViewById<View>(R.id.self_esteem_3) as RadioGroup
        rg_se_4 = findViewById<View>(R.id.self_esteem_4) as RadioGroup
        rg_se_5 = findViewById<View>(R.id.self_esteem_5) as RadioGroup
        rg_se_6 = findViewById<View>(R.id.self_esteem_6) as RadioGroup
        rg_se_7 = findViewById<View>(R.id.self_esteem_7) as RadioGroup
        rg_se_8 = findViewById<View>(R.id.self_esteem_8) as RadioGroup
        rg_se_9 = findViewById<View>(R.id.self_esteem_9) as RadioGroup
        rg_se_10 = findViewById<View>(R.id.self_esteem_10) as RadioGroup

        val selectedSelfEsteem1: Int = rg_se_1!!.checkedRadioButtonId
        val selectedSelfEsteem2: Int = rg_se_2!!.checkedRadioButtonId
        val selectedSelfEsteem3: Int = rg_se_3!!.checkedRadioButtonId
        val selectedSelfEsteem4: Int = rg_se_4!!.checkedRadioButtonId
        val selectedSelfEsteem5: Int = rg_se_5!!.checkedRadioButtonId
        val selectedSelfEsteem6: Int = rg_se_6!!.checkedRadioButtonId
        val selectedSelfEsteem7: Int = rg_se_7!!.checkedRadioButtonId
        val selectedSelfEsteem8: Int = rg_se_8!!.checkedRadioButtonId
        val selectedSelfEsteem9: Int = rg_se_9!!.checkedRadioButtonId
        val selectedSelfEsteem10: Int = rg_se_10!!.checkedRadioButtonId

        var self_esteem_1 = findViewById(selectedSelfEsteem1) as RadioButton
        var self_esteem_2 = findViewById(selectedSelfEsteem2) as RadioButton
        var self_esteem_3 = findViewById(selectedSelfEsteem3) as RadioButton
        var self_esteem_4 = findViewById(selectedSelfEsteem4) as RadioButton
        var self_esteem_5 = findViewById(selectedSelfEsteem5) as RadioButton
        var self_esteem_6 = findViewById(selectedSelfEsteem6) as RadioButton
        var self_esteem_7 = findViewById(selectedSelfEsteem7) as RadioButton
        var self_esteem_8 = findViewById(selectedSelfEsteem8) as RadioButton
        var self_esteem_9 = findViewById(selectedSelfEsteem9) as RadioButton
        var self_esteem_10 = findViewById(selectedSelfEsteem10) as RadioButton

        for(i in 1..10){
            var se = self_esteem_1.text
            if(i == 1){
                se = self_esteem_1.text
            }else if(i == 2){
                se = self_esteem_2.text
            }else if(i == 3){
                se = self_esteem_3.text
            }else if(i == 4){
                se = self_esteem_4.text
            }else if(i == 5){
                se = self_esteem_5.text
            }else if(i == 6){
                se = self_esteem_6.text
            }else if(i == 7){
                se = self_esteem_7.text
            }else if(i == 8){
                se = self_esteem_8.text
            }else if(i == 9){
                se = self_esteem_9.text
            }else if(i == 10){
                se = self_esteem_10.text
            }

            if (i == 2 || i == 5 || i == 6 || i == 8 || i == 9){
                if(se == "Sangat Setuju"){
                    calculate += 1.0
                }else if(se == "Setuju"){
                    calculate += 2.0
                }else if(se == "Tidak Setuju"){
                    calculate += 3.0
                }else if(se == "Sangat Tidak Setuju"){
                    calculate += 4.0
                }
            }else{
                if(se == "Sangat Setuju"){
                    calculate += 4.0
                }else if(se == "Setuju"){
                    calculate += 3.0
                }else if(se == "Tidak Setuju"){
                    calculate += 2.0
                }else if(se == "Sangat Tidak Setuju"){
                    calculate += 1.0
                }
            }
        }

        if(calculate>30.0){
            calculate = 30.0
        }

        calculate /= 30

        return calculate
    }

    fun get_mental_health_history():Double {
        var temp:Double = -1.0
        var mhs = findViewById<View>(R.id.mental_health_history) as RadioGroup
        var selectedmental_health_history: Int = mhs!!.checkedRadioButtonId
        var mental_health_history = findViewById(selectedmental_health_history) as RadioButton

        var mental = mental_health_history.text
        if (mental == "Tidak pernah"){
            temp = 0.0
        }else if(mental == "Ya, saya pernah"){
            temp = 1.0
        }
        return temp
    }

    fun get_headache():Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.headache) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Tidak, saya jarang mengalami sakit kepala"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Ya, saya sering mengalami sakit kepala"){
            temp = 1.0
        }
        return temp
    }

    fun get_blood_pressure():Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.blood_pressure) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Tekanan darah saya rendah"){
            temp = 0.0
        }else if(txt == "Tekanan darah saya normal"){
            temp = 0.5
        }else if(txt == "Ya, saya memiliki tekanan darah yang tinggi"){
            temp = 1.0
        }
        return temp
    }

    fun get_sleep_quality():Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.sleep_quality) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, saya selalu tidur dengan nyenyak"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya tidak memiliki kualitas tidur yang baik"){
            temp = 1.0
        }
        return temp
    }

    fun get_breathing_problem():Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.breathing_problem) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, pernafasan saya baik-baik saja"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, pernafasan saya terganggu"){
            temp = 1.0
        }
        return temp
    }

    fun get_noise_level(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.noise_level) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Tidak, lingkungan saya tidak ribut"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Ya, lingkungan saya ribut"){
            temp = 1.0
        }
        return temp
    }

    fun get_living_conditions(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.living_conditions) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, kondisi kehidupan saya baik"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, kondisi kehidupan saya tidak baik"){
            temp = 1.0
        }
        return temp
    }

    fun get_safety(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.safety) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, tempat tinggal saya aman"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, tempat tinggal saya tidak aman"){
            temp = 1.0
        }
        return temp
    }

    fun get_basic_needs(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.basic_needs) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, kebutuhan dasar saya terpenuhi"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, kebutuhan dasar saya tidak terpenuhi"){
            temp = 1.0
        }
        return temp
    }

    fun get_academic_performance(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.academic_performance) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, saya yakin"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya tidak yakin"){
            temp = 1.0
        }
        return temp
    }

    fun get_study_load(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.study_load) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, beban studi saya ringan"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, beban studi saya berat"){
            temp = 1.0
        }
        return temp
    }

    fun get_teacher_student_relationship(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.student_teacher_relationship) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, saya memiliki hubungan yang baik dengan dosen saya"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya tidak memiliki hubungan yang baik dengan dosen saya"){
            temp = 1.0
        }
        return temp
    }

    fun get_future_career_concerns(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.future_career_concerns) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, saya merasa yakin dengan karir saya nanti"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya tidak yakin dengan karir saya nanti"){
            temp = 1.0
        }
        return temp
    }

    fun get_social_support(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.social_support) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, mendapatkan dukungan sosial"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya jarang mendapatkan dukungan sosial"){
            temp = 1.0
        }
        return temp
    }

    fun get_peer_pressure(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.peer_pressure) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Tidak, saya jarang mendapatkan tekanan dari lingkungan"){
            temp = 0.0
        }else if(txt == "Pernah, tetapi tidak sering"){
            temp = 0.5
        }else if(txt == "Ya, saya sering mendapatkan tekanan"){
            temp = 1.0
        }
        return temp
    }

    fun get_extracurricular_activities(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.extracurricular_activities) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Ya, saya sering melakukan kegiatan ekstrakurikuler"){
            temp = 0.0
        }else if(txt == "Biasa Saja"){
            temp = 0.5
        }else if(txt == "Tidak, saya jarang melakukan kegiatan ekstrakurikuler"){
            temp = 1.0
        }
        return temp
    }

    fun get_bullying(): Double {
        var temp:Double = -1.0
        var selectedGroup = findViewById<View>(R.id.bullying) as RadioGroup
        var selected: Int = selectedGroup!!.checkedRadioButtonId
        var selectedView = findViewById(selected) as RadioButton

        var txt = selectedView.text
        if (txt == "Tidak, saya jarang dirundung"){
            temp = 0.0
        }else if(txt == "Pernah, tetapi tidak sering"){
            temp = 0.5
        }else if(txt == "Ya, saya sering mendapatkan perundungan"){
            temp = 1.0
        }
        return temp
    }
}

private operator fun <K, V> MutableMap<K, V>.set(v: V, value: V?) {

}
