#!/usr/bin/env bb
(require '[babashka.process :refer [shell]])

(def suites
  ["test/hirameki/methods/test_hirameki_edn.cljc"
   "test/hirameki/methods/test_analyze.cljc"
   "test/hirameki/methods/test_cid.cljc"
   "test/hirameki/methods/test_dataset.cljc"
   "test/hirameki/methods/test_ingest.cljc"
   "test/hirameki/methods/test_kotoba.cljc"
   "test/hirameki/methods/test_autorun.cljc"
   "test/hirameki/methods/test_social.cljc"])

(let [failed (reduce (fn [acc suite]
                       (println "==" suite "==")
                       (let [{:keys [exit]} (shell {:continue true} "bb" suite)]
                         (cond-> acc (not (zero? exit)) (conj suite))))
                     [] suites)]
  (System/exit (if (empty? failed) 0 1)))
