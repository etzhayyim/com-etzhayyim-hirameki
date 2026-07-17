(ns hirameki.methods.test-social
  (:require [clojure.test :refer [deftest is run-tests]]
            [hirameki.methods.social :as social]))

(deftest publication-adapter-is-repository-native
  (is (fn? social/draft-observation-post))
  (is (fn? social/build-live))
  (is (string? social/DISCLAIMER)))

#?(:clj
   (let [{:keys [fail error]} (run-tests 'hirameki.methods.test-social)]
     (when (pos? (+ fail error)) (System/exit 1))))
