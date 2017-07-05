package com.androidworkshop.viresh.towerofhanoi;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int maxTowerSize = 4;
    StackClass stack1, stack2,stack3;
    TextView A,B,C,from,to,Ring;
    boolean chronometerIsRunning;
    Chronometer CM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stack1 = new StackClass();
        stack2 = new StackClass();
        stack3 = new StackClass();
        setContentView(R.layout.activity_main);
        A = (TextView) findViewById(R.id.stack1);
        B = (TextView) findViewById(R.id.stack2);
        C = (TextView) findViewById(R.id.stack3);
        from = (TextView) findViewById(R.id.fromTxt);
        to = (TextView) findViewById(R.id.toTxt);
        Ring = (TextView)findViewById(R.id.textView13);
        Ring.setText(maxTowerSize+"");
        chronometerIsRunning=false;
        CM  = (Chronometer) findViewById(R.id.chronometer3);
        reset();
    }

    void reset(){
        CM.stop();
        chronometerIsRunning = false;
        stack1.size = 0;
        stack2.size = 0;
        stack3.size = 0;
        A.setText("");
        B.setText("");
        C.setText("");
        for(int i=maxTowerSize; i>=1; i--) {
            stack1.insert(i);
        }
        populate();
        from.setText("A");
        to.setText("B");
    }

    public void reset(View V){
        reset();
    }

    void populate(){
        String p="",q="",r="";
        for(int i=stack1.size-1; i>=0; i--){
            if(i!=0){
                p = p+stack1.a[i]+"\n";
            }
            else{
                p = p+stack1.a[i];
            }
        }

        for(int i=stack2.size-1; i>=0; i--){
            if(i!=0){
                q = q+stack2.a[i]+"\n";
            }
            else{
                q = q+stack2.a[i];
            }
        }

        for(int i=stack3.size-1; i>=0; i--) {
            if (i != 0) {
                r = r + stack3.a[i] + "\n";
            } else {
                r = r + stack3.a[i];
            }
        }

        A.setText(p);
        B.setText(q);
        C.setText(r);
    }

    public void onFromIncrement(View V){
        if(from.getText() == "A"){
            from.setText("B");
        }
        else if(from.getText() == "B"){
            from.setText("C");
        }
    }

    public void onFromDecrement(View V){
        if(from.getText() == "B"){
            from.setText("A");
        }
        else if(from.getText() == "C"){
            from.setText("B");
        }
    }

    public void onToIncrement(View V){
        if(to.getText() == "A"){
            to.setText("B");
        }
        else if(to.getText() == "B"){
            to.setText("C");
        }
    }

    public void onToDecrement(View V){
        if(to.getText() == "B"){
            to.setText("A");
        }
        else if(to.getText() == "C"){
            to.setText("B");
        }
    }

    void sendAlertToast(String p){
        Toast.makeText(MainActivity.this, p,Toast.LENGTH_SHORT).show();
    }

    public void manualMove(View v){
        if(chronometerIsRunning == false){
            chronometerIsRunning=true;
            CM.setBase(SystemClock.elapsedRealtime());
            CM.start();
        }
        if(from.getText()==to.getText()){
            sendAlertToast("Invalid Choice of Towers.");
            return;
        }

        int numFrom, numTo;
        StackClass f,t;
        if(from.getText()=="A"){
            numFrom = stack1.top();
            f = stack1;
        }
        else if(from.getText()=="B"){
            numFrom = stack2.top();
            f = stack2;
        }
        else{
            numFrom = stack3.top();
            f = stack3;
        }

        if(numFrom == 0){
            sendAlertToast("No rings in From Tower !!!");
            return;
        }

        if(to.getText()=="A"){
            numTo = stack1.top();
            t = stack1;
        }
        else if(to.getText()=="B"){
            numTo = stack2.top();
            t = stack2;
        }
        else{
            numTo = stack3.top();
            t = stack3;
        }

        if(numFrom >  numTo && numTo !=0){
            sendAlertToast("Cannot place a taller ring on top of shorter ring.");
            return;
        }

        t.insert(f.top());
        f.pop();

        populate();

        if(hasWon()){
            CM.stop();
            chronometerIsRunning = false;
            sendAlertToast("Congratulations, you completed the puzzle !!!");
        }
    }

    public void onRingInc(View v){
        int x= Integer.parseInt(Ring.getText()+"");
        if(x+1>6){
            sendAlertToast("Limit of rings reached.");
            return;
        }
        maxTowerSize++;
        reset();
        Ring.setText(""+maxTowerSize);
    }

    public void onRingDec(View v){
        int x= Integer.parseInt(Ring.getText()+"");
        if(x-1<3){
            sendAlertToast("Limit of rings reached.");
            return;
        }
        maxTowerSize--;
        reset();
        Ring.setText(maxTowerSize+"");
    }

    boolean hasWon(){
        if(stack1.size !=0){
            return false;
        }
        else{
            if(stack2.size ==0 || stack3.size==0){
                return true;
            }
            else{
                return  false;
            }
        }
    }
}
