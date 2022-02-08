package com.software.development.softwaredevelopment.chapter4.bookcode4;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(File file) throws IOException;
}
