(ns arbsqrt.core-test
  (:require [clojure.test :refer :all]
            [arbsqrt.core :refer :all]))

(deftest utility-fns
  (testing "Testing utility functions."
  	(is (= (pct-error 100 101) -0.01))))