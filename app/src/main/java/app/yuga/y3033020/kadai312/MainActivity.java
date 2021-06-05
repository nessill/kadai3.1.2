package app.yuga.y3033020.kadai312;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //センサを管理するマネージャ
    private SensorManager manager;//1.0

    //アプリ開始時の処理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //マネージャを取得
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);//1.0
    }

    public void onResume() {
        super.onResume();
        //　明るさセンサ(TYPWE_LIGHT)のリストを取得
        List<Sensor> sensors =
                manager.getSensorList(Sensor.TYPE_LIGHT);//1.0
        // 一つ以上見つかったら、差塩のセンサを取得してリスナーに登録
        if (sensors.size() != 0) {
            Sensor sensor = sensors.get(0);////1.0
            manager.registerListener(
                    this, sensor, SensorManager.SENSOR_DELAY_NORMAL);//1.0
        }
    }



    protected void onPause() {
        super.onPause();
        //　一時停止の際にリスナー登録を解除
        manager.unregisterListener(this);//1.0
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }//1.0
    public void onSensorChanged(SensorEvent arg0) {//1.0
        if (arg0.sensor.getType() == Sensor.TYPE_LIGHT) {
            float intensity = arg0.values[0];//1.0
            String str = intensity + "ルクス";
            TextView textview = (TextView) findViewById(R.id.status_text);
            textview.setText(str);
        }

    }
}