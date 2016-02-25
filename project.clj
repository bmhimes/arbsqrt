(defproject arbsqrt "1.0"
  :description "Estimates the square root of a number."
  :url "https://github.com/bmhimes/arbsqrt"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
  				 [org.clojure/tools.cli "0.3.3"]
  				 [org.clojure/math.numeric-tower "0.0.4"]
  				 [com.taoensso/timbre "4.2.1"]]
  :main arbsqrt.core)
