//
//  main.cpp
//  15651
//
//  Created by 이원창 on 2021/01/17.
//
#include <iostream>
#include <algorithm>
using namespace std;

bool visit[9];
int arr[8] = {0, };
int n = 0;
int m = 0;
int cnt = 0;
void go(){
    if(cnt == m - 1){
        for(int i = 0; i < m; i++){
            cout << arr[i] << ' ';
        }
        cout << '\n';
    }
    else{
        for(int i = 1; i <= n; i++){
            cnt++;
            arr[cnt] = i;
            go();
            cnt--;
        }
    }
}

int main(int argc, const char * argv[]) {
    cin >> n >> m;
    
    for(int i = 1; i <= n; i++){
        arr[cnt] = i;
        go();
    }
    
    return 0;
}

