#include <bits/stdc++.h>
using namespace std;
    
    double squareRoot(double x) {
        if (x < 0) 
        {
            cout<<"Sqaure Root no possible"<<endl;
        }
    
        double left = 0, right = x;
        double epsilon = 1e-6;
    
        while (right - left > epsilon) {
            double mid = left + (right - left) / 2;
            if (mid * mid < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (left + right) / 2; 
    }
    
    int main() {
        double number = 25;
        cout << "Square root of " << number << " is: " << squareRoot(number) << endl;
        return 0;
    }
    
