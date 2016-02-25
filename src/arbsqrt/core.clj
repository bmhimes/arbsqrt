(ns arbsqrt.core
	(:require [clojure.tools.cli :refer [parse-opts]]
			  [clojure.math.numeric-tower :as math]))

(def cli-options
	[["-s" "--square SQUARE" "Squared number for which root will be estimated."
	  :parse-fn #(Double/parseDouble %)
	  :validate [#(> % 0) "Must be a number greater than 0."]]
	 ["-p" "--precision PRECISION" "Precision of root estimate, as a maximum percentage error of the estimated square."
	  :parse-fn #(Double/parseDouble %)
	  :validate [#(<= (math/expt 10 -13) % 100.0) "Must be a number between 10e-14 and 1."]]])

(defn abs
	"Returns the absolute value.  This function works with Big Decimals."
	[num]
	(if (> num 0)
		num
		(* num -1)))

(defn abs-pct-error
	"Returns the absolute percentage error."
	[actual est]
	(let [diff (- actual est)
		  abs-diff (abs diff)
		  pct-diff (/ abs-diff actual)]
		pct-diff))

(defn calc-precision-scale
	"Calculates the Big Decimal scale to use based on a desired precision."
	[precision]
	(math/ceil (abs (Math/log10 precision))))

(defn fmt-pct
	"Formats doubles as percentages for human consumption."
	[pct & {:keys [sci-dec]
		    :or {sci-dec 1}}]
	(let [scaled-pct (* pct 100.0)]
		(if (< pct 0.0001)
			(format (clojure.string/join ["%1." sci-dec "e%%"]) scaled-pct)
			(format "%2.2f%%" scaled-pct))))

(defn ratio-to-big-dec
	"Converts ratios to Big Decimals based on scale."
	[src-ratio scale]
	(let [src-num (bigdec (.numerator src-ratio))
		  src-denom (bigdec (.denominator src-ratio))
		  result (.divide src-num src-denom scale java.math.RoundingMode/HALF_UP)]
		  result))

(defn sqrt-est
	"Estimates a new square root based on the square and the prior root estimate."
	[square old-est]
	(let [dividend (/ square old-est)
		  new-est (/ (+ old-est dividend) 2.0)]
		  new-est))

(defn precision-est
	"Estimates the square root of a number to the desired precision, as a percentage error in the square."
	[square pct-precision]
	(loop [new-est (sqrt-est square (/ square 2.0))]
		(let [square-est (* new-est new-est)
			  error (abs-pct-error square square-est)]
			  (if (<= error pct-precision)
				new-est
				(recur (sqrt-est square new-est))))))

(defn -main [& args]
  (let [parsed-cli-opts (parse-opts args cli-options)
  		square (:square (:options parsed-cli-opts))
  		precision (:precision (:options parsed-cli-opts))
  		final-root-est (precision-est square precision)
  		final-square-est (* final-root-est final-root-est)
  		square-error (abs-pct-error square final-square-est)]
  		(do 
  			(println "Square: " square)
  			(println "Precision: " (fmt-pct precision))
  			(println "Final root estimate: " final-root-est)
  			(println "Final square estimate: " final-square-est)
  			(println "Error: " (fmt-pct square-error)))))