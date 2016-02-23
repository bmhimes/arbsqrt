(ns arbsqrt.core)

(defn abs-pct-error
	[actual est]
	(double (/ (Math/abs (- actual est)) actual)))

(defn sqrt-est
	[square old-est]
	(let [dividend (/ square old-est)
		  new-est (/ (+ old-est dividend) 2)]
		  new-est))

(defn precision-est
	[square pct-precision]
	(loop [new-est (sqrt-est square (/ square 2))]
		(let [square-est (* new-est new-est)
			  square-diff (- square-est square)
			  error (/ square-diff square-est)]
			  (if (<= error pct-precision)
				new-est
				(recur (sqrt-est square new-est))))))

(defn -main [& args]
  (println "Hello, World!")
  (let [square 12
  		precision 0.01]
  		(println (precision-est square precision))))