(ns advent-of-code.2020.day-04
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))


(defn input-data
  []
  (->> (io/resource "2020/day-04.txt")
       (slurp)
       (str/split-lines)
       (partition-by str/blank?)
       (remove (partial every? str/blank?))
       (map (partial str/join " "))))


;;; Part 1

(def required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})


(defn problem-1
  [input]
  (->> input
       (filter #(->> (re-seq #"(\w+):" %)
                     (map second)
                     (set)
                     (set/subset? required-fields)))
       (count)))


(comment
  (problem-1 (input-data)))


;;; Part 2

(def field->validator
  {"byr" (fn [birth-year]
           (and (= 4 (count birth-year))
                (<= 1920 (Integer/parseInt birth-year) 2002)))
   "iyr" (fn [issue-year]
           (and (= 4 (count issue-year))
                (<= 2010 (Integer/parseInt issue-year) 2020)))
   "eyr" (fn [expiration-year]
           (and (= 4 (count expiration-year))
                (<= 2020 (Integer/parseInt expiration-year) 2030)))
   "hgt" (fn [height]
           (let [[_ height unit] (re-find #"^(\d+)(cm|in)$" height)]
             (and height unit
                  (or (and (= "cm" unit)
                           (<= 150 (Integer/parseInt height) 193))
                      (and (= "in" unit)
                           (<= 59 (Integer/parseInt height) 76))))))
   "hcl" (fn [hair-color] (some? (re-find #"^#[0-9a-f]{6}$" hair-color)))
   "ecl" (fn [eye-color] (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} eye-color))
   "pid" (fn [passport-id] (some? (re-find #"^[\d+]{9}$" passport-id)))})


(defn parse-passport
  [s]
  (->> (re-seq #"(\w+):([#\w]+)" s)
       (map (fn [[_ field value]] {field value}))
       (apply merge)))


(defn valid-passport?
  [passport]
  (and (set/subset? (set (keys field->validator))
                    (set (keys passport)))
       (every?
         (fn [[field validator]]
           (validator (get passport field)))
         field->validator)))


(defn problem-2
  [input]
  (count (filter #(valid-passport? (parse-passport %)) input)))


(comment
  (problem-2 (input-data)))
