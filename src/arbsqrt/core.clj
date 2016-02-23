(ns arbsqrt.core)

(defn sqrt-est
	[square old-est]
	(let [dividend (/ square old-est)
		  new-est (/ (+ old-est dividend) 2)]
		  new-est))

(defn -main [& args]
  (println "Hello, World!")
  (let [square 12
  		first-est (/ square 2)]
  		(println (sqrt-est square first-est))))