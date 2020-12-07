(ns advent-of-code.2020.day-06
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))


(defn get-input
  []
  (map #(str/split % #"\s+")
       (str/split (slurp (io/resource "2020/day-06.txt")) #"\n\n")))


;;; Part 1

(defn problem-1
  [input]
  (->> (map str/join input)
       (map set)
       (map count)
       (reduce +)))


;;; Part 2

(defn all-yes
  [group]
  (->> (map set group)
       (apply set/intersection)
       (count)))


(defn problem-2
  [input]
  (->> (map all-yes input)
       (reduce +)))
