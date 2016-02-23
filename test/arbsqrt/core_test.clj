(ns arbsqrt.core-test
  (:require [clojure.test :refer :all]
            [arbsqrt.core :refer :all]))

(deftest java-interop
	(testing "Testing Java math functions.")
		(is (= 1 (java.lang.Math/abs -1))))

(deftest utility-fns
  (testing "Testing utility functions."
  	(is (= (abs-pct-error 100 101) 0.01))
  	(is (= (abs-pct-error 50 100) 1.0))
  	(is (= (abs-pct-error 100 50) 0.5))))

(deftest core-fns
  (testing "Testing square estimate iteration."
  	(is (= 4.0 (sqrt-est 12 6)))
  	(is (= 3.5 (sqrt-est 12 4)))))