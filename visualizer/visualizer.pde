import java.util.Collections;

ArrayList<Float> data = new ArrayList<Float>();
int semester;
int class_id;
float numbers[] = {22, 10, 15, 26, 30, 35, 32};
PFont font;

void setup(){
  size(1000, 600);
  font = createFont("Arial",15,true);
  String lines[] = loadStrings("/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-classes/cmsc-classes-washed/1008.csv");
  float a=0, b=0, c=0, d=0, w=0, f=0, pass=0, I=0, ng=0;
  String p[];
  for (int i=0; i < lines.length; i++) {
    //println(lines[i]);
    p = splitTokens(lines[i], ",");
    semester = int(p[0]);
    class_id = int(p[2]);
    //println(p[4]);
    if(p[4].equals("A")) a++;
    if(p[4].equals("B")) b++;
    if(p[4].equals("C")) c++;
    if(p[4].equals("D")) d++;
    if(p[4].equals("W")) w++;
    if(p[4].equals("F")) f++;
    if(p[4].equals("I")) I++;
    if(p[4].equals("P")) pass++;
    if(p[4].equals("NG")) ng++;
          //for(int j=0; j < p.length; j++){
            //print(p[j] + " ");
          //}
          //println("");
  }
  println("As: " + a);
  println("Bs: " + b);
  println("Cs: " + c);
  println("Ds: " + d);
  println("Fs: " + f);
  println("Ws: " + w);
  println("Is: " + I);
  println("Ps: " + pass);
  println("NGs: " + ng);
  float total = a + b + c + d + w + f + I + pass + ng;
  println("Total: " + total);
  data.add(0, a);
  data.add(1, b);
  data.add(2, c);
  data.add(3, d);
  data.add(4, f);
  data.add(5, w);
  data.add(6, I);
  data.add(7, pass);
  data.add(8, ng);
  println(data);
}
      
void draw(){
  background(100);
  textFont(font, 15);
  textAlign(CENTER);
  text("Grades of All Students Taking CMSC Courses for a Given Semester", width/2, 20);
  textAlign(LEFT);
  drawBarChart(data);
}
      
void drawBarChart(ArrayList<Float> numbers){
  int i = 0;
  String nums[] = {"A: ", "B: ", "C: ", "D: ", "F: ", "W: ", "Incomplete: ", "Pass: ", "NG: "};
  for (i = 0; i < numbers.size(); i++) {
    float w = map(numbers.get(i), 0, Collections.max(numbers), 0, width - 75);
    rect(10, ((i + 1) * 25) + 5, w, 20);
    text(nums[i] + numbers.get(i), w + 15, ((i + 1) * 25) + 20 );
  }
  text("Semester: " + semester, 10, ((i+1)*25)+20);
  text("Class: " + class_id, 10, ((i+1)*25) + 40);
}