^{:nextjournal.clerk/visibility {:code :hide}}
(ns huey.sftp
  (:require [java-time.api :as jt]
            [clojure.string :as str])
  (:import (huey SftpService)))

(defn ls-entry->map[ls-entry]
  (-> ls-entry
      bean
      ((fn [x] (merge x (bean (:attrs x)))))
      (select-keys [:filename :size :dir :permissionsString])))

(defn ls [sftp dir]
  (->> (.ls (.getChannel sftp) dir)
       (map ls-entry->map)
       (map #(assoc % :path (str dir "/" (:filename %))))))

(defn gen-yyyyMMdd-dirs[start-yyyy start-MM start-dd number-of-days]
  (map #(jt/format "yyyy/MM/dd" (.plusDays (jt/local-date-time start-yyyy start-MM start-dd) %)) (range number-of-days)))

(defn create[sftp content path]
   (.put sftp content path))

(defn cat[sftp path]
  (.get sftp path))

(defn connect-sftp-pw[hostname username pw]
  (SftpService/connectWithPw hostname username pw))

(defn connect-sftp-keypair[hostname username]
  (SftpService/connectWithKeyPair hostname username))