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
			  (is (= 12.0 parsed-square))
			  (is (= 0.01 parsed-precision))
			  (is (= nil cli-errors)))))

(deftest java-interop
	(testing "Testing Java math functions.")
		(is (= 1 (java.lang.Math/abs -1))))

(deftest utility-fns
  (testing "Testing error functions."
  	(is (= (abs-pct-error 100 101) 0.01))
  	(is (= (abs-pct-error 50 100) 1.0))
  	(is (= (abs-pct-error 100 50) 0.5)))
  (testing "Testing formatting functions."
  	(is (= "100.00%" (fmt-pct 1.0)))
  	(is (= "1.00%" (fmt-pct 0.01)))
  	(is (= "0.01%" (fmt-pct 0.0001)))
  	(is (= "1.0e-03%" (fmt-pct 0.00001)))))

(deftest core-fns
  (testing "Testing square estimate iteration."
  	(is (= 4.0 (sqrt-est 12 6)))
  	(is (= 3.5 (sqrt-est 12 4)))))