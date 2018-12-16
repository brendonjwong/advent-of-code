(ns advent-of-code.2018.day-04
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(def data
  (->> (io/resource "2018/day-04.txt")
       slurp
       str/split-lines))


;;; Part 1

;; Examples records:
;; [1518-11-01 00:00] Guard #10 begins shift
;; [1518-11-01 00:05] falls asleep
;; [1518-11-01 00:25] wakes up
(def record-pattern #"^\[([\d\s-:]+)\] (Guard|falls|wakes) #?([\d]+)?")


(defn record->map
  [record]
  (zipmap
    [:timestamp :action :id]
    (rest (re-find record-pattern record))))


(defn sleep-log
  [records]
  (dissoc
    (reduce (fn [{:keys [current-guard] :as log}
                 {:keys [action id timestamp] :as record}]
              (let [minute (when timestamp (->> (- (count timestamp) 2)
                                                (subs timestamp)
                                                (Integer/parseInt)))]
                (case action
                  "Guard" (assoc log :current-guard id)
                  "falls" (update log current-guard #(if % (conj % minute) [minute]))
                  "wakes" (update log current-guard #(if % (conj % minute) [minute])))))
            {}
            records)
    :current-guard))


(defn compute-minutes
  [log [id sleep-intervals]]
  (->> (partition 2 sleep-intervals)
       (mapcat (fn [[start end]] (concat (range start end))))
       (assoc log id)))


(defn mode
  "Get the most frequently occurring value in the collection."
  [coll]
  (apply max-key val (frequencies coll)))


(defn problem-1
  [data]
  (let [sleepiest-guard (->> (sort data)
                             (map record->map)
                             (sleep-log)
                             (reduce compute-minutes {})
                             (apply max-key #(-> % val count)))]
    (* (Integer/parseInt (first sleepiest-guard))
       (->> (second sleepiest-guard)
            (mode)
            (first)))))


;;; Part 2


(defn problem-2
  [data]
  (let [guard-id->minutes (->> (sort data)
                               (map record->map)
                               (sleep-log)
                               (reduce compute-minutes {}))
        guard (->> guard-id->minutes
                   (map (fn [[id minutes]]
                          {:id id :minute (first (mode minutes)) :freq (second (mode minutes))}))
                   (apply max-key :freq))]
    (* (Integer/parseInt (:id guard))
       (:minute guard))))
