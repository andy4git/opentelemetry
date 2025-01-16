package trace.otel;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

public class OpenTelemetryConfig {
	private static OpenTelemetry openTelemetry;

	public static OpenTelemetry initializeOpenTelemetry() {
		if (openTelemetry == null) {
			// Configure OTLP exporter
			OtlpGrpcSpanExporter spanExporter = OtlpGrpcSpanExporter.builder().setEndpoint("http://localhost:4317").build();

			// Configure the TracerProvider with the exporter
			SdkTracerProvider tracerProvider = SdkTracerProvider.builder().addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build()).build();

			// Initialize OpenTelemetry
			openTelemetry = OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();
		}
		return openTelemetry;
	}

	public static Tracer getTracer() {
		initializeOpenTelemetry();
		return openTelemetry.getTracer("my-application", "1.0.0");
	}
}
