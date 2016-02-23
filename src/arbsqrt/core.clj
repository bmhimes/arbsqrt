(ns arbsqrt.core)

(defn abs-pct-error
	[actual est]
	(let [diff (- actual est)
		  abs-diff (java.lang.Math/abs diff)
		  pct-diff (/ abs-diff actual)]
		(double pct-diff)))

(defn sqrt-est
	[square old-est]
	(let [dividend (/ square old-est)
		  new-est (/ (+ old-est dividend) 2)]
		  (double new-est)))

(defn precision-est
	[square pct-precision]
	(loop [new-est (sqrt-est square (/ square 2))]
		(let [square-est (* new-est new-est)
			  error (abs-pct-error square square-est)]
			  (if (<= error pct-precision)
				new-est
				(recur (sqrt-est square new-est))))))

(defn -main [& args]
  (println "Hello, World!")
  (let [square 12
  		precision 0.01
  		final-root-est (precision-est square precision)
  		final-square-est (* final-root-est final-root-est)
  		square-error (abs-pct-error square final-square-est)]
  		(do 
  			(println "Square: " square)
  			(println "Precision: " precision)
  			(println "Final root estimate: " final-root-est)
  			(println "Final square estimate: " final-square-est)
  			(println "Error: " square-error))))