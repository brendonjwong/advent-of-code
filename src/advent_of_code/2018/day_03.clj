(ns advent-of-code.2018.day-03
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))


(def data
  (->> (io/resource "advent-of-code/2018/day-03.txt")
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


(defn compute-width
  [claim]
  (map (fn [x] [(+ (:from-left claim) x) (:from-top claim)])
       (range (:width claim))))


(defn compute-matrix
  "Given a claim with an x and y position (`:from-left` and `:from-top`,
  respectively), as well as `:height` and `:width`, returns a list of
  indices (represented by vectors)."
  [claim]
  (->> (range (:height claim))
       (map #(update claim :from-top + %))
       (mapcat compute-width)
       (into #{})))


(defn problem-1
  [data]
  (->> (map parse-claim data)
       (mapcat compute-matrix)
       (frequencies)
       (filter #(< 1 (val %)))
       (count)))


;;; Part 2

(defn problem-2
  [data]
  (let [id->indices (->> (map parse-claim data)
                         (mapcat #(hash-map (:id %) (compute-matrix %))))
        overlaps (->> (vals id->indices)
                      (apply concat)
                      (frequencies)
                      (filter #(< 1 (val %)))
                      (keys)
                      (into #{}))]
    (->> (filter #(empty? (set/intersection (second %) overlaps)) id->indices)
         (first)
         (first))))
