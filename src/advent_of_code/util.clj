(ns advent-of-code.util
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(defn get-input
  [path]
  (->> (io/resource path)
       (slurp)
       (str/split-lines)))
