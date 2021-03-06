#include <iostream>

using namespace std;

int gcd(int a, int b) {
    if(b == 0) {
        return a;
    }
    else {
        return gcd(b, a % b);
    }
}

int main() {
    int a, b;
    cin >> a >> b;

    int gcdn = gcd(a, b);
    cout << gcdn << '\n';
    cout << a * b / gcdn << '\n';

    return 0;
}