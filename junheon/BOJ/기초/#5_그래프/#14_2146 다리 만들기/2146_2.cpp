// 인접 행렬을 이용한 bfs

#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

using namespace std;
// g -> 그래프 / c -> 섬 그룹 / d -> 다른 섬과의 거리
int g[100][100];
int c[100][100];
int d[100][100];
int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};
int n;

// bfs를 이용하여 같은 섬끼리 그룹지음
void bfs_group(int sx, int sy, int cnt) {
    queue<pair<int, int>> q;
    q.push(make_pair(sx, sy));
    c[sx][sy] = cnt;
    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if(c[nx][ny] == 0 && g[nx][ny] == 1) {
                    c[nx][ny] = cnt;
                    q.push(make_pair(nx, ny));
                }
            }
        }
    }
}

// bfs를 이용하여 한 섬에서 다른 섬까지의 거리 계산
int bfs_dist(int cnt) {
    int ans = -1;
    queue<pair<int, int>> q;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(g[i][j] == 1) {
                q.push(make_pair(i, j));
                d[i][j] = 0;
            }
            else {
                d[i][j] = -1;
            }
        }
    }
    // 모든 섬을 큐에 넣은 후,
    // 다음 점이 바다라면, 인접한 섬의 그룹과 거리를 넣음
    // 다음 점이 섬이고 현재 섬 그룹과 다르다면, 현재 섬과 다른 섬과의 거리 계산
    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if(d[nx][ny] == -1) {
                    d[nx][ny] = d[x][y] + 1;
                    c[nx][ny] = c[x][y];
                    q.push(make_pair(nx, ny));
                }
                else {
                    if(c[nx][ny] != c[x][y]) {
                        if(ans == -1 || ans > d[x][y] + d[nx][ny]) {
                            ans = d[x][y] + d[nx][ny];
                        }
                    }
                }
            }
        }
    }

    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cin >> g[i][j];
        }
    }
    // 같은 섬끼리 그룹 만듦
    int cnt = 0;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(c[i][j] == 0 && g[i][j] == 1) {
                cnt++;
                bfs_group(i, j, cnt);
            }
        }
    }
    // 다른 섬까지의 거리 계산
    cout << bfs_dist(cnt) << '\n';
    return 0;
}