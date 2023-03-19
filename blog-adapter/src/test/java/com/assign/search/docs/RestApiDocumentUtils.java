package com.assign.search.docs;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

public class RestApiDocumentUtils {

  public static OperationRequestPreprocessor getDocumentRequest() {
    return preprocessRequest(
        modifyUris().removePort(),
        prettyPrint()
    );
  }

  public static OperationResponsePreprocessor getDocumentResponse() {
    return preprocessResponse(
        prettyPrint());
  }

}
