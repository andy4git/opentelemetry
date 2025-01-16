package trace.otel;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

public class Application {
	
	private static final Tracer tracer = OpenTelemetryConfig.getTracer();

	public static void main(String[] args) {
		// Start a span
		Span span = tracer.spanBuilder("my-operation").setAttribute("key", "value").startSpan();

		try {
			System.out.println("Running a traced operation!");
		} 
		finally {
			span.end();
		}
	}
}
