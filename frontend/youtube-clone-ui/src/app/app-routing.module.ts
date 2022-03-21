  import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
  import {UploadVideoComponent} from "./upload-video/upload-video.component";
  import {SaveVideoMetadataComponent} from "./save-video-metadata/save-video-metadata.component";

const routes: Routes = [
  {
    path:'upload',
    component:UploadVideoComponent,
  },
  {
    path:'edit/:videoId',
    component:SaveVideoMetadataComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
