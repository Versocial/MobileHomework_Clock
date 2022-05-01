import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mobilehomework_clock.R
import java.text.SimpleDateFormat
import java.util.*

class TextClockFragment : Fragment() {

    private lateinit var textClock: TextView
    private lateinit var timer:Timer
    private val sdf: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
    private val period:Long=500
    private val handler:Handler=object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            textClock.text =msg.data.getString("time")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val view:View = inflater.inflate(R.layout.fragment_text_clock, container, false)
        textClock=view.findViewById(R.id.text_clock)
        return view
    }

    override fun onResume() {
        super.onResume()
        timer=Timer("textClock")
        timer.schedule(object : TimerTask() {
            override fun run() {
                val info=sdf.format(Date())
                handler.sendMessage(Message.obtain().apply { data=Bundle().apply { putString("time",info) } })
            }
        },0,period)
    }

    override fun onPause() {
        timer.cancel()
        val info ="pause when ${textClock.text}"
        textClock.text=info
        super.onPause()
    }
}
    