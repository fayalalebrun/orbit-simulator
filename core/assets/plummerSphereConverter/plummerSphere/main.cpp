#include <iostream>
#include <fstream>
#include <random>

//std::random_device rd;     // only used once to initialise (seed) engine
//std::mt19937 rng(rd());    // random-number engine used (Mersenne-Twister in this case)
//std::uniform_int_distribution<int> uni(min,max); // guaranteed unbiased

//auto random_integer = uni(rng);
using namespace std;

int main () {
    std::random_device rd; // random
    std::mt19937 rng(rd()); // random
    std::uniform_int_distribution<int> uni(1,8); //random
    
    int counter = 0; // onject count
    string line, line2, line3, line4, line5, line6, line7; //text input variables
    
        string outputPath = "/Users/davrockenzahn19/Desktop/plummer_sphere_(127_stars,_random_colors).txt"; // input path
    
        string inputPath = "/Users/davrockenzahn19/Downloads/tab128-2.txt"; //output path
    
     cout << endl << "Define the input path and filename: ";
     cin >> inputPath;
     cout << endl << "Define the output path and filename: ";
     cin >> outputPath;
    cout << endl;
    
       ifstream input (inputPath);
    ofstream output (outputPath);

    
    if (input.is_open())
    {
        output << "v2\n" <<endl;
        while (counter != 128)
        {
            input >> line >> line2 >> line3 >> line4 >> line5 >> line6 >> line7;
            cout << "generationg object number: " << counter << endl;
            
            if (output.is_open())
            {
                output << "name: Sun" << counter << endl;
                output << "radius: 6957.0" << endl;
                output << "mass: 0.0078125" << endl;
                output << "vX: " << line5 << endl;
                output << "vY: " << line6 << endl;
                output << "vZ: " << line7 << endl;
                auto random_integer = uni(rng);
                output << "texture: ./Planet Textures/Star_texture_" << random_integer << ".png" << endl;
                output << "color-r: 1.0" << endl;
                output << "color-g: 1.0" << endl;
                output << "color-b: 1.0" << endl;
                output << "color-a: 1.0" << endl;
                output << "x: " << line2 << endl;
                output << "y: " << line3 << endl;
                output << "z: " << line4 << endl << endl;
                
                
            }
            counter ++;
        }
        
        cout << endl << "---------------------------------" << endl << "Process completed" << endl;
        cout << "Output path is: "  << endl << outputPath << endl << endl;
    }
    
    else cout << "Can't open file" << endl;
    
    
    
    output.close();
    input.close();
    return 0;
}

//v2

//name: Sun
//radius: 695700.0
//mass: 333054.0
//vX: -8.0950519636734785678
//vY: 10.079420705729289054
//vZ: 0.18130337691409920131
//texture: ./Planet Textures/Sun_texture.png
//color-r: 0.8901961
//color-g: 0.6509804
//color-b: 0.0
//color-a: 1.0
//x: 2.578904369581140E-03
//y: 5.322460861326624E-03
//z: -1.383592844831597E-04
