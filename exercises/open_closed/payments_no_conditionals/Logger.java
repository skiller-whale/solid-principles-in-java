// This is a sufficiently general interface that it can allows for a variety of
// different implementations with different amounts of data.
import cards.*;

public interface Logger {
  void log(double amount, Card card);
}
