package mx.tec.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.notkamui.keval.Keval
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var spinnerConvert: Spinner
    lateinit var spinnerResult: Spinner
    lateinit var convertNum: TextView
    lateinit var resultNum: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerConvert = findViewById<Spinner>(R.id.spinner1)
        spinnerResult = findViewById<Spinner>(R.id.spinner2)
        val buttonConv = findViewById<Button>(R.id.buttonConvert)
        convertNum = findViewById<TextView>(R.id.convertNumber)
        resultNum = findViewById<TextView>(R.id.convertResult)
        var currency = listOf("USD", "EUR", "MXN", "BTC", "GBP")
        var adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, currency)

        spinnerConvert.adapter = adapter
        spinnerResult.adapter = adapter
        buttonConv.setOnClickListener(this@MainActivity)
    }
    override fun onClick(view: View?){
        when(view!!.id){
            R.id.buttonConvert -> {
                if(convertNum.text.toString() == ""){
                    println("No pasa nada")
                } else{
                    var evalString = ""
                    if(spinnerConvert.selectedItem.toString() == "USD"){
                        evalString += convertNum.text.toString()
                    }else if(spinnerConvert.selectedItem.toString() == "EUR"){
                        evalString += convertNum.text.toString() + "/0.84"
                    }else if(spinnerConvert.selectedItem.toString() == "MXN"){
                        evalString += convertNum.text.toString() + "/19.97"
                    }else if(spinnerConvert.selectedItem.toString() == "BTC"){
                        evalString += convertNum.text.toString() + "/0.000020"
                    }else{
                        evalString += convertNum.text.toString() + "/0.72"
                    }
                    var result = Keval.eval(evalString)
                    if(spinnerResult.selectedItem.toString() == "USD"){
                        evalString = evalString
                    }else if(spinnerResult.selectedItem.toString() == "EUR"){
                        evalString += "*0.84"
                    }else if(spinnerResult.selectedItem.toString() == "MXN"){
                        evalString += "*19.97"
                    }else if(spinnerResult.selectedItem.toString() == "BTC"){
                        evalString += "*0.000020"
                    }else{
                        evalString += "*0.72"
                    }
                    result = Keval.eval(evalString)
                    result = String.format("%.7f", result).toDouble()
                    resultNum.text = result.toString()
                }
            }
        }
    }
}