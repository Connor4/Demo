# Demo

   import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    
    val n = scanner.nextInt()
    val positions = IntArray(n) { scanner.nextInt() }
    val numS = scanner.nextInt() 
    val gases = IntArray(n) { scanner.nextInt() }
    val d = scanner.nextInt()
    val k = scanner.nextInt()
    
    val stations = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        if (positions[i] < d) {
            stations.add(positions[i] to gases[i])
        }
    }
    stations.sortBy { it.first }
    
    val pq = PriorityQueue<Int>(compareByDescending { it })
    var currentEnd = k  
    var stops = 0
    var i = 0
    
    while (currentEnd < d) {
       
        while (i < stations.size && stations[i].first <= currentEnd) {
            pq.add(stations[i].second)
            i++
        }
        
        if (pq.isEmpty()) {
            println(-1)
            return
        }
        
        currentEnd += pq.poll()
        stops++
    }
    
    println(stops)
}
