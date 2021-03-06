// queue 2�� ���

#include <iostream>
#include <queue>
#include <cstring>

using namespace std;
int d[100001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n, k;
    cin >> n >> k;
    memset(d, -1, sizeof(d));
    queue<int> q;
    queue<int> nq;
    q.push(n);
    d[n] = 0;
    while(!q.empty()) {
        int s = q.front();
        q.pop();
        int n3 = s * 2;
        if(n3 <= 100000 && d[n3] == -1) {
            q.push(n3);
            d[n3] = d[s];
        }
        int n2 = s - 1;
        if(n2 >= 0 && d[n2] == -1) {
            nq.push(n2);
            d[n2] = d[s] + 1;
        }
        int n1 = s + 1;
        if(n1 <= 100000 && d[n1] == -1) {
            nq.push(n1);
            d[n1] = d[s] + 1;
        }
        if(q.empty()) {
            q = nq;
            nq = queue<int>();
        }
    }
    cout << d[k] << '\n';
       
    return 0;
}