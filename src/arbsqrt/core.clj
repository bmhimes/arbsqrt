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
  		final-est (precision-est square precision)]
  		(println final-est)))