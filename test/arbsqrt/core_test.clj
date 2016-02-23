(ns arbsqrt.core-test
  (:require [clojure.test :refer :all]
            [arbsqrt.core :refer :all]))

(deftest utility-fns
  (testing "Testing utility functions."
  	(is (= (pct-error 100 101) -0.01))))

(deftest core-fns
  (testing "Testing square estimate iteration."
  	(is (= 4 (sqrt-est 12 6)))))