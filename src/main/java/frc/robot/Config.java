package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

public class Config
{

  static private Config       config = null;
  static private Preferences preferences = null;

  private Config()
  {
    // Get the preferences singleton
    preferences = Preferences.getInstance();
  }

  public static Config getInstance()
  {
    // If we haven't already instantiated a Config object, instantiate one right now.
    if (config == null)
    {
      config = new Config();
    }
    
    // Return the previously- or just-instantiated Config object
    return config;
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultInt(String key, int value) 
  {
    int prefValue;

    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putInt(key, value);
    }
    else if ((prefValue = preferences.getInt(key, Integer.MIN_VALUE)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultLong(String key, long value) 
  {
    long prefValue;

    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putLong(key, value);
    }
    else if ((prefValue = preferences.getLong(key, Long.MIN_VALUE)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultFloat(String key, float value) 
  {
    float prefValue;
    
    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putFloat(key, value);
    }
    else if ((prefValue = preferences.getFloat(key, Float.MIN_VALUE)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultDouble(String key, double value) 
  {
    double prefValue;
    
    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putDouble(key, value);
    }
    else if ((prefValue = preferences.getDouble(key, Double.MIN_VALUE)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultString(String key, String value) 
  {
    String prefValue;

    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putString(key, value);
    }
    else if ((prefValue = preferences.getString(key, null)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Sets the default for a given key. If no value has previously been saved
   * for the given key, then the specified value will be saved as the key's
   * value.
   *
   * @param key The key
   * @param value The value to use for this key if no value is already specified
   */
  public void setDefaultBoolean(String key, boolean value) 
  {
    boolean prefValue;

    // If this key hasn't been saved...
    if (! preferences.containsKey(key))
    {
        // ... then save our value
        preferences.putBoolean(key, value);
    }
    else if ((prefValue = preferences.getBoolean(key, false)) != value)
    {
      // Comparison of the previously-saved value with the current default
      // specified in the code shows that the code is out of date (or a
      // different value was saved inadvertently)
      System.out.println(
        "UPDATE THE CODE: OBSOLETE DEFAULT WILL NOT OVERWRITE PREFERENCES VALUE:" +
        "key=" + key + ", code says:" + value + ", should say:" + prefValue);
    }
  }

  /**
   * Returns the value at the given key. It is expected that the key will have
   * been previously initialized with a call to the setDefaultInt method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public int getInt(String key) 
  {
    return preferences.getInt(key, Integer.MIN_VALUE);
  }

  /**
   * Returns the value at the given key. It is expected that the key will have
   * been previously initialized with a call to the setDefaultLong method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public long getLong(String key) 
  {
    return preferences.getLong(key, Long.MIN_VALUE);
  }

  /**
   * Returns the value at the given key. It is expected that the key will
   * have been previously initialized with a call to the setDefaultFloat method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public float getFloat(String key) 
  {
    return preferences.getFloat(key, Float.MIN_VALUE);
  }

  /**
   * Returns the value at the given key. It is expected that the key will
   * have been previously initialized with a call to the setDefaultDouble method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public double getDouble(String key) 
  {
    return preferences.getDouble(key, Double.MIN_VALUE);
  }

  /**
   * Returns the value at the given key. It is expected that the key will
   * have been previously initialized with a call to the setDefaultString method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public String getString(String key) 
  {
    return preferences.getString(key, null);
  }

  /**
   * Returns the value at the given key. It is expected that the key will
   * have been previously initialized with a call to the setDefaultBoolean method.
   *
   * @param key The key
   * @return The previously saved value corresponding to this key.
   */
  public Boolean getBoolean(String key) 
  {
    return preferences.getBoolean(key, false);
  }
}