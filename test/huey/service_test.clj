(ns huey.service-test
  (:require [clojure.test :refer :all]
            [huey.service :refer :all]))
(deftest abc-test
  (testing "abc"
    (let [result (abc {})]
      (is (= (:status result) 200)))))
