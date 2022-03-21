import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VideoResponse} from "./upload-video/videoResponse";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File):Observable<VideoResponse> {

    const formData = new FormData()
    formData.append('file', file, file.name)

    return this.httpClient.post<VideoResponse>("http://localhost:8080/api/videos",formData);
  }
}
