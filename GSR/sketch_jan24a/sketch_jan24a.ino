const int GSR = A0; //getting signal from pin A0
int sensorValue = 0;
int gsr_average = 0;

void setup(){
  Serial.begin(9600);
}

void loop(){
  long sum = 0;
  for (int i = 0; i < 10; i++) { //10 is recommened to remove glitches
    sensorValue = analogRead(GSR);
    sum += sensorValue;
    delay(5);
  }

  gsr_average = sum/10;
  Serial.println(gsr_average); //Serial_Port_Reading is the value display on Serial Port(between 0~1023)
  //Serial.println(sensorValue);
}

