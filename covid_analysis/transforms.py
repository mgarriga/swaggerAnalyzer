import pandas as pd

# filter by country code
def filter_country(pdf, country="USA"):
  pdf = pdf[pdf.iso_code == country]
  return pdf

# pivot by indicator and fill missing values
def pivot_and_clean(pdf,fillna):
  pdf["value"] = pd.to_numeric(pdf["value"], errors="coerce")
  pdf = pdf.fillna(fillna).pivot_table(values="value",columns="indicator",index="date")
  return pdf

# create column names that are compatible with delta tables
def clean_spark_cols(pdf):
  pdf.columns = [c.replace(" ", "_").lower() for c in pdf.columns]
  return pdf

# convert index to column (works with pandas api on spark too)
def index_to_col(df,colname):
  df[colname] = df.index
  return df
