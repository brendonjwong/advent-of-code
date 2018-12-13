(ns advent-of-code.2018.day-03
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))


(def data
  (->> (io/resource "2018/day-03.txt")
       slurp
       str/split-lines))


;;; Part 1

;; Example claim: "#123 @ 3,2: 5x4"
(def claim-pattern #"^#([\d]+) @ ([\d]+),([\d]+): ([\d]+)x([\d]+)")


(defn parse-claim
  [claim]
  (->> (rest (re-matches claim-pattern claim))
       (map #(Integer/parseInt %))
       (zipmap [:id :from-left :from-top :width :height])))


(defn compute-locations
  [{:keys [from-left from-top width height] :as claim}]
  (for [x (range from-left (+ from-left width))
        y (range from-top (+ from-top height))]
    [x y]))


(defn problem-1
  [data]
  (->> (map parse-claim data)
       (mapcat compute-locations)
       (frequencies)
       (filter #(< 1 (val %)))
       (count)))


;;; Part 2

(defn problem-2
  [data]
  (let [id->indices (->> (map parse-claim data)
                         (mapcat #(hash-map (:id %) (set (compute-locations %)))))
        overlaps (->> (vals id->indices)
                      (apply concat)
                      (frequencies)
                      (filter #(< 1 (val %)))
                      (keys)
                      (into #{}))]
    (->> (filter #(empty? (set/intersection (second %) overlaps)) id->indices)
         (first)
         (first))))
