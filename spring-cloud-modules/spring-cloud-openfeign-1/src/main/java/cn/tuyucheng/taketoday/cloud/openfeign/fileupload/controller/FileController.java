package cn.tuyucheng.taketoday.cloud.openfeign.fileupload.controller;

import cn.tuyucheng.taketoday.cloud.openfeign.fileupload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@Autowired
	private UploadService service;

	@PostMapping(value = "/upload")
	public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
		return service.uploadFile(file);
	}

	@PostMapping(value = "/upload-mannual-client")
	public boolean handleFileUploadWithManualClient(
		@RequestPart(value = "file") MultipartFile file) {
		return service.uploadFileWithManualClient(file);
	}

	@PostMapping(value = "/upload-error")
	public String handleFileUploadError(@RequestPart(value = "file") MultipartFile file) {
		return service.uploadFile(file);
	}

}