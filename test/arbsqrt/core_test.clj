(ns arbsqrt.core-test
  (:require [clojure.test :refer :all]
            [arbsqrt.core :refer :all]))

(deftest cli-opts
	(testing "Valid CLI option parsing."
		(let [cli-args ["-s" "12" "-p" "0.01"]
			  parsed-opts (clojure.tools.cli/parse-opts cli-args cli-options)
			  cli-errors (:errors parsed-opts)
			  parsed-square (:square (:options parsed-opts))
			  parsed-precision (:precision (:options parsed-opts))]
			  (is (= 12.0M parsed-square))
			  (is (= 0.01M parsed-precision))
			  (is (= nil cli-errors)))))

(deftest java-interop
	(testing "Testing Java math functions.")
		(is (= 1 (java.lang.Math/abs -1))))

(deftest utility-fns
  (testing "Testing error functions."
  	(is (= (abs-pct-error 100 101) 1/100))
  	(is (= (abs-pct-error 50 100) 1))
  	(is (= (abs-pct-error 100 50) 1/2)))
  (testing "Testing formatting functions."
  	(is (= "100.00%" (fmt-pct 1.0)))
  	(is (= "1.00%" (fmt-pct 0.01)))
  	(is (= "0.01%" (fmt-pct 0.0001)))
  	(is (= "1.0e-03%" (fmt-pct 0.00001))))
  (testing "Testing precision scale functions."
  	(is (= 2.0 (calc-precision-scale 0.01)))
  	(is (= 3.0 (calc-precision-scale 0.005)))
  	(is (= 3.0 (calc-precision-scale 0.001))))
  (testing "Ratio to Big Decimal conversion functions."
  	(is (= 0.33M (ratio-to-big-dec 1/3 2)))))

(deftest core-fns
  (testing "Testing square estimate iteration."
  	(is (= 4 (sqrt-est 12 6)))
  	(is (= 7/2 (sqrt-est 12 4)))))