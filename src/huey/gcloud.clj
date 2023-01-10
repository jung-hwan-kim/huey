(ns huey.gcloud
  (:require [clojure.java.io :as io])
  (:import (huey GcloudStorageHandler)))

(defn cat[bucket-name blob-name]
  (GcloudStorageService/readBlob (GcloudStorageService/getBlob bucket-name blob-name)))

(defn ls
  ([bucket-name]
   (->> (GcloudStorageService/listBlob bucket-name)
       .iterateAll
       .iterator
       iterator-seq
       (map bean)
       (map #(select-keys % [:name :createTimeOffsetDateTime :generation :directory :size :contentType]))))
  ([bucket-name prefix]
   (->> (GcloudStorageService/listBlob bucket-name prefix)
        .iterateAll
        .iterator
        iterator-seq
        (map bean)
        (map #(select-keys % [:name :createTimeOffsetDateTime :generation :directory :size :contentType])))))

(defn create[file-content-in-str bucket-name blob-name ]
  (with-open [in (io/input-stream (.getBytes file-content-in-str))]
    (GcloudStorageService/create in bucket-name blob-name)))

(defn update[file-content-in-str bucket-name blob-name ]
  (let [blob (GcloudStorageService/getBlob bucket-name blob-name)]
    (GcloudStorageService/update file-content-in-str blob)))
