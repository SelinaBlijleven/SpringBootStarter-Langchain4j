package org.example.springbootstarterlangchain4j.models.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

public class EmbeddingStoreBuilder {

    // Simple InMemory implementation, but we could use different types of embedding stores for this.
    InMemoryEmbeddingStore<TextSegment> embeddingStore;

    public EmbeddingStoreBuilder() {
        embeddingStore = new InMemoryEmbeddingStore<>();
    }

    public static EmbeddingStore<TextSegment> fromPath(String path) {
        return new EmbeddingStoreBuilder().addDocumentsFromPath(path).build();
    }

    private void addDocuments(List<Document> documents) {
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
    }

    public EmbeddingStoreBuilder addDocumentsFromPath(String documentPath) {
        // Read the documents with a prebuilt loader, so we don't have to think
        // about file types
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(documentPath);
        // Use the internal addDocuments function
        addDocuments(documents);

        // allow chaining
        return this;
    }

    public EmbeddingStore<TextSegment> build() {
        return embeddingStore;
    }
}
